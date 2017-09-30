/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import sun.misc.BASE64Encoder;

/**
 *
 * @author BharathKumar
 */
@WebServlet(urlPatterns = {"/ComputeHashes"})
public class ComputeHashes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String data = request.getParameter("Data");
            String Algorithm = request.getParameter("Hash");
            StringBuffer sb = new StringBuffer();
            //This module computes the MD5 value and converts it to Base 64 
            if(Algorithm.equals("MD5")){
                MessageDigest InputAlgorithm = MessageDigest.getInstance("MD5");
                InputAlgorithm.update(data.getBytes());
                byte Bytedata[] = InputAlgorithm.digest();
                for (int i = 0; i < Bytedata.length; i++) {
                    sb.append(Integer.toString((Bytedata[i] & 0xff) + 0x100, 16).substring(1));
                }
                BASE64Encoder Base64encoder = new BASE64Encoder();
                String Base64 = Base64encoder.encode(Bytedata);
                out.println("</h2>" + "<h2>" + "Hashes of the String : " + data);
                out.println("<h2>" + "MD5 (Hex) : " + sb.toString().toUpperCase() + "</h2>");
                out.println("<h2>" + "MD5 (Base 64) : " + Base64 + "</h2>");
            }
            
            //This module computes the SHA-1 value and converts it to Base 64
            if(Algorithm.equals("SHA")){
                MessageDigest InputAlgorithm = MessageDigest.getInstance("SHA-1");
                InputAlgorithm.update(data.getBytes());
                byte Bytedata[] = InputAlgorithm.digest();
                for (int i = 0; i < Bytedata.length; i++) {
                    sb.append(Integer.toString((Bytedata[i] & 0xff) + 0x100, 16).substring(1));
                }
                BASE64Encoder Base64encoder = new BASE64Encoder();
                String Base64 = Base64encoder.encode(Bytedata);
                out.println("</h2>" + "<h2>" + "Hashes of the String : " + data);
                out.println("<h2>" + "SHA-1 (Hex) : " + sb.toString().toUpperCase() + "</h2>");
                out.println("<h2>" + "SHA-1 (Base 64) : " + Base64 + "</h2>");
            }
            out.print("<a href='/Project-1_Task-01/index.jsp'><h2>Click here to go back to main menu</h2></a>");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ComputeHashes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ComputeHashes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
