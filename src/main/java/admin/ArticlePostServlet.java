package admin;

import db.ArticleDao;
import model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.GregorianCalendar;

/**
 * @author zhengzhizhao
 *         Created at 2016/12/14
 */
@WebServlet(
        name = "articlePostServlet",
        urlPatterns = "/admin/article-post"
)
public class ArticlePostServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("edit") != null){
            Article article = ArticleDao.getArticle(Long.parseLong(req.getParameter("id")));
            req.setAttribute("article",article);
        }

        req.getRequestDispatcher("/WEB-INF/jsp/admin/articlePost.jsp")
                .forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
        Article article = new Article();
        article.setTitle(req.getParameter("article-title"));
        article.setType(req.getParameter("article-type"));
        article.setLabels(req.getParameter("article-labels").split(","));
        article.setCreatedAt(GregorianCalendar.getInstance().getTime());
        article.setContent(req.getParameter("article-content"));
        ArticleDao.insertArticle(article);
    }
}