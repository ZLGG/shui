package com.gs.lshly.common.struct.platadmin.user.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserLabelDictQTO implements Serializable {

    @Data
    @ApiModel("UserLabelDictQTO.QTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("UserLabelDictQTO.UserIdListQTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class UserIdListQTO extends BaseQTO {

        @ApiModelProperty(value = "会员ID数组")
        private List<String> userIdList;

    }

    @Data
    @ApiModel("UserLabelDictQTO.UserIdQTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class UserIdQTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID")
        private String userId;
    }

}
