
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author by wyl
 * @date 2021/11/28.19点02分
 */


/**
 * 继承BaseMapper<T>
 * CRUD已经被Mybatis-plus实现
 */

@Mapper
@Repository(value = "userMapper")
public interface UserMapper extends BaseMapper<User> {

}
