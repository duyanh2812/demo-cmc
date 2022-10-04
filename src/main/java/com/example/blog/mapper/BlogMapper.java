package com.example.blog.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogVo;

@Mapper
public interface BlogMapper {
	@Insert("INSERT INTO blog (user_id, title, description)" +
            "VALUES (#{userId}, #{title}, #{description})")
	int insertBlog(BlogVo blogVo);
	public List<BlogVo> getBlogs(BlogDto input);
	@Select("SELECT * from blog WHERE id = #{blogId}")
    @Results(value = {
            @Result(property = "id", column = "id")
           ,@Result(property = "userId", column = "user_id")
           ,@Result(property = "title", column = "title")
           ,@Result(property = "description", column = "description")})
	public BlogVo getBlogById(BigInteger blogId);
	@Delete("delete from blog WHERE id = #{blogId}")
	public int deleteBlogById(BigInteger blogId);
	@Update("UPDATE blog\n" +
	"SET tile = #{title}\n" +
	"description = #{description}\n" +
	"updated_dtm = CURRENT_TIMESTAMP()" +
	"updated_id = #{updatedId}")
	public int updateBlogById(BlogDto input);
}
