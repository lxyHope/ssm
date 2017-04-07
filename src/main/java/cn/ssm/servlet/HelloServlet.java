package cn.ssm.servlet;
import cn.ssm.mapper.MapMaper;
import cn.ssm.model.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
    private static SqlSessionFactory sessionFactory;
    private static Reader reader;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        //连接数据库
        reader = Resources.getResourceAsReader("mybatis.xml");
        sessionFactory = new SqlSessionFactoryBuilder().build(reader);

        //打开一个sql session
        SqlSession sqlSession = sessionFactory.openSession();

        //将sql session 映射到 mapper类
        MapMaper mapMapper = sqlSession.getMapper(MapMaper.class);
        Map map = mapMapper.findMapById(1) ;

        //输出json
        PrintWriter out = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, map);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
