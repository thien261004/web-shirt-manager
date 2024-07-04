package controllers;

import entities.mapping_entities.SanPham;
import entities.custom.SanPhamChiTietCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import respositories.jdbc.KichThuocRepository;
import respositories.jdbc.MauSacRepository;
import respositories.jdbc.SanPhamChiTietRepository;
import respositories.jdbc.SanPhamRepository;

import java.io.IOException;
import java.util.List;

@WebServlet({
        "/san_pham_chi_tiet/index",
        "/san_pham_chi_tiet/create",
        "/san_pham_chi_tiet/store",
        "/san_pham_chi_tiet/edit",
        "/san_pham_chi_tiet/update",
        "/san_pham_chi_tiet/delete",
})
public class SanPhamChiTietServlet extends HttpServlet {
    private SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
    private SanPhamRepository spRepo = new SanPhamRepository();
    private MauSacRepository msRepo = new MauSacRepository();

    private KichThuocRepository ktRepo = new KichThuocRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("create")) {
//            this.create(req, res);
        } else if (uri.contains("edit")) {
//            this.edit(req, res);
        } else if (uri.contains("delete")) {
//            this.delete(req, res);
        } else {
            this.index(req, res);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String uri = req.getRequestURI();
        if (uri.contains("store")) {
//            this.store(req, res);
        } else if (uri.contains("update")) {
//            this.update(req, res);
        } else {
            //
        }
    }

    protected  void index(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String idS = req.getParameter("idSanPham");
        if (idS != null && idS.trim().length() != 0) {
            int idSP = Integer.parseInt(idS.trim());
            SanPham sp = this.spRepo.findByID(idSP);
            List<SanPhamChiTietCustom> listSPCT = this.spctRepo.findByIdSP(idSP);
            req.setAttribute("sanPham", sp);
            req.setAttribute("data",listSPCT);
            req.getRequestDispatcher("/views/san_pham_chi_tiet/index.jsp")
                    .forward(req, res);
        } else {
            res.sendRedirect("/san-pham/index");
        }
    }

}