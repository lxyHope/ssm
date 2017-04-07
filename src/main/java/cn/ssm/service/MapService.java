package cn.ssm.service;

import cn.ssm.mapper.MapMaper;
import cn.ssm.model.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/map")
public class MapService {
    private static SqlSessionFactory sessionFactory;
    private static ApplicationContext ctx;
    private static MapMaper mapMaper;

    @RequestMapping(value="{id}",method = RequestMethod.GET)
    public @ResponseBody Map findMapById(@PathVariable int id){
        String mybatisResource = "mybatis.xml";
        try{
            InputStream ins = Resources.getResourceAsStream(mybatisResource);
            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(ins);
        }catch(Exception e){
            e.printStackTrace();
        }

        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();

        MapMaper mapMapper = session.getMapper(MapMaper.class);
        Map map = mapMapper.findMapById(1) ;

        return map ;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<Map> findMapList() {

        String mybatisResource = "mybatis.xml";

        /* 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件） */
        try{
            InputStream ins = Resources.getResourceAsStream(mybatisResource);
            //构建sqlSession的工厂
            sessionFactory = new SqlSessionFactoryBuilder().build(ins);
        }catch(Exception e){
            e.printStackTrace();
        }

        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();
        MapMaper mapMapper = session.getMapper(MapMaper.class);

        List<Map> map = mapMapper.findMapList() ;

        return map ;
    }
}
