package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -7034623579245779110L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		// Get parameter
//		String docPath = req.getParameter("doc_path");
//		ServletOutputStream out = resp.getOutputStream();
//		DataInputStream in = new DataInputStream(new FileInputStream(new File(docPath)));
//		
//		byte[] bytes = new byte[1024];
//		int length = 0;
//		while ((in != null) && ((length = in.read(bytes)) != -1)) {
//			out.write(bytes, 0, length);
//		}
//		in.close();
//		out.flush();
//		resp.flushBuffer();
//		
//		resp.sendRedirect(req.getHeader("Referer"));
		
		
		File file = new File(req.getParameter("doc_path"));
		ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        resp.setContentType(mimeType != null? mimeType:"application/octet-stream");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + req.getParameter("doc_name") + "\"");
         
        ServletOutputStream os       = resp.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read=0;
        while((read = fis.read(bufferData))!= -1){
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
        System.out.println("File downloaded at client successfully");
		
		
		
	}
	
	

}
