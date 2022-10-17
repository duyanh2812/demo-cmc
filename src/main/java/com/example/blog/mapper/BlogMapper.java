package com.example.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;
import com.example.product.model.Category;
import com.example.product.model.Image;

@Mapper
public interface BlogMapper {
	@Insert("INSERT INTO blog (id, title, description, created_id)" +
            "VALUES (#{id},#{title}, #{description}, #{createdId})")
	int insertBlog(BlogVo blogVo);
	public List<BlogVo> getBlogs(@Param(value = "input") BlogDto input, @Param("current_page") int current_page, @Param("page_size") int page_size);
	@Select("select blog.id as id, blog.title as title, blog.description as description, user.name as user_name, blog.created_dtm as created_dtm\n"
			+ "from blog as blog\n"
			+ "left join user as user on user.id = blog.created_id\n"
			+ "WHERE blog.id = #{blogId}")
    @Results(value = {
            @Result(property = "id", column = "id")
           ,@Result(property = "createdDtm", column = "created_dtm")
           ,@Result(property = "userName", column = "user_name")
           ,@Result(property = "title", column = "title")
           ,@Result(property = "description", column = "description")})
	public BlogVo getBlogById(@Param(value = "blogId") String blogId);
	@Delete("delete from blog WHERE id = #{blogId}")
	public int deleteBlogById(@Param(value="blogId") String blogId);
	@Update("UPDATE blog\n" +
	"SET tile = #{title}\n" +
	"description = #{description}\n" +
	"updated_dtm = CURRENT_TIMESTAMP()" +
	"updated_id = #{updatedId}")
	public int updateBlogById(@Param(value="blogDto") BlogDto input);
	public int countAllBlogs(@Param(value="input") BlogDto input);
	@Select("SELECT url from image WHERE blog_id = #{blogId}")
    @Results(value = {
            @Result(property = "url", column = "url")})
	List<Image> getImagesByBlogId(String blogId);
	@Select("SELECT cate.name, cate.id from product_category as pc\n"
			+ "INNER JOIN category as cate on cate.id = pc.category_id\n"
			+ "WHERE pc.blog_id = #{blogId}")
    @Results(value = {
            @Result(property = "name", column = "name")
            ,@Result(property = "id", column = "id")})
	List<Category> getCategoriesByBlogId(String blogId);
}
