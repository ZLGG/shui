package ${cfg.daoImplPackage};

import ${cfg.entityPackage}.${entity};
import ${cfg.mapperPackage}.${entity}Mapper;
import ${cfg.daoPackage}.I${entity}Repository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author ${author}
 * @since ${date}
*/
@Service
public class ${entity}RepositoryImpl extends ServiceImpl<${entity}Mapper, ${entity}> implements I${entity}Repository {

}