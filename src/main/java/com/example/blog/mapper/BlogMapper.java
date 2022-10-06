package com.example.blog.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;

@Mapper
public interface BlogMapper {
	@Insert("INSERT INTO blog (title, description, created_id)" +
            "VALUES (#{title}, #{description}, #{createdId})")
	int insertBlog(BlogVo blogVo);
	public List<BlogVo> getBlogs(@Param(value = "input") BlogDto input, @Param("current_page") int current_page, @Param("page_size") int page_size);
	@Select("SELECT * from blog WHERE id = #{blogId}")
    @Results(value = {
            @Result(property = "id", column = "id")
           ,@Result(property = "userId", column = "user_id")
           ,@Result(property = "title", column = "title")
           ,@Result(property = "description", column = "description")})
	public BlogVo getBlogById(@Param(value = "blogId") Long blogId);
	@Delete("delete from blog WHERE id = #{blogId}")
	public int deleteBlogById(@Param(value="blogId") Long blogId);
	@Update("UPDATE blog\n" +
	"SET tile = #{title}\n" +
	"description = #{description}\n" +
	"updated_dtm = CURRENT_TIMESTAMP()" +
	"updated_id = #{updatedId}")
	public int updateBlogById(@Param(value="blogDto") BlogDto input);
}
