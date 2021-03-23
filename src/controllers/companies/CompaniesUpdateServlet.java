package controllers.companies;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Company;
import models.validators.CompanyValidator;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesUpdateServlet
 */
@WebServlet("/companies/update")
public class CompaniesUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompaniesUpdateServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Company c = em.find(Company.class, (Integer)(request.getSession().getAttribute("company_id")));

            // 現在の値と異なる取引先コードが入力されていたら
            // 重複チェックを行う指定をする
            Boolean codeDuplicateCheckFlag = true;
            if(c.getCode().equals(request.getParameter("code"))) {
                codeDuplicateCheckFlag = false;
            } else {
                c.setCode(request.getParameter("code"));
            }

            c.setName(request.getParameter("name"));
            c.setContent(request.getParameter("content"));
            c.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            c.setDelete_flag(0);

            List<String> errors = CompanyValidator.validate(c, codeDuplicateCheckFlag);
            if(errors.size() > 0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("company", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/companies/edit.jsp");
                rd.forward(request, response);
            } else {
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("company_id");

                response.sendRedirect(request.getContextPath() + "/companies/index");
            }
        }
    }

}