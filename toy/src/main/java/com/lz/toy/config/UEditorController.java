package com.lz.toy.config;
import com.lz.toy.ueditor.ActionEnter;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ClassUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

@WebServlet(name = "UEditorServlet", urlPatterns = "/UEditor")
public class UEditorController extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application=this.getServletContext();
        String srcPath = null;
        try {
            srcPath = ClassUtils.getDefaultClassLoader().getResource("").toURI().getPath().substring(1,ClassUtils.getDefaultClassLoader().getResource("").toURI().getPath().length()-1);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String rootPath = application.getRealPath("/");
        File resFile = new File("/"+srcPath+"/config.json");
        File distFile = new File("/"+rootPath+"/config.json");
        if(!distFile.exists()){
            FileUtils.copyFileToDirectory(resFile, new File(rootPath), false);
        }
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        String result = new ActionEnter( request, rootPath ).exec();
        if( action!=null &&
                (action.equals("listfile") || action.equals("listimage") ) ){
            rootPath = rootPath.replace("\\", "/");
            result = result.replaceAll(rootPath, "/");
        }
        out.write( result );
    }
}