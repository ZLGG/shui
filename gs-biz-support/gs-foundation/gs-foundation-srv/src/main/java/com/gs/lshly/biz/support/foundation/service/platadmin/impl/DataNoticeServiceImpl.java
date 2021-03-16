package com.gs.lshly.biz.support.foundation.service.platadmin.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataNotice;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeRecv;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeType;
import com.gs.lshly.biz.support.foundation.enums.NoticeReadStateEnum;
import com.gs.lshly.biz.support.foundation.enums.NoticeScopeTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.DataNoticeMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.DataNoticeView;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRecvRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeTypeRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataNoticeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.MerchantShopDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataNoticeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataNoticeQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lxus
 * @since 2020-09-14
 */
@Component
public class DataNoticeServiceImpl implements IDataNoticeService {

    @Autowired
    private IDataNoticeRepository repository;

    @Autowired
    private IDataNoticeTypeRepository dataNoticeTypeRepository;

    @Autowired
    private IDataNoticeRecvRepository dataNoticeRecvRepository;

    @Autowired
    private DataNoticeMapper dataNoticeMapper;

    @DubboReference
    private ICommonShopRpc  commonShopRpc;

    @Override
    public PageData<DataNoticeVO.ListVO> pageData(DataNoticeQTO.QTO qto) {

        QueryWrapper<DataNoticeView> queryWrapper =  MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getTitle())){
            queryWrapper.like("nt.title",qto.getTitle());
        }
        IPage<DataNoticeView> page = MybatisPlusUtil.pager(qto);
        dataNoticeMapper.mapperPage(page,queryWrapper);
        if(ObjectUtils.isEmpty(page.getRecords())){
            return MybatisPlusUtil.toPageData(new ArrayList<>(),qto.getPageNum(),qto.getPageSize(),page.getTotal());
        }
        List<DataNoticeVO.ListVO> voList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(page.getRecords())){
            List<String> shopIdList = new ArrayList<>();
            for(DataNoticeView view:page.getRecords()){
                DataNoticeVO.ListVO listVO = new DataNoticeVO.ListVO();
                BeanUtils.copyProperties(view,listVO);
                voList.add(listVO);
                if(null != view.getShopId()){
                    shopIdList.add(view.getShopId());
                }else{
                    listVO.setShopName("全部");
                }
            }
            if(ObjectUtils.isNotEmpty(shopIdList)){
               List<CommonShopVO.ShopIdNameVO>  shopIdNameList = commonShopRpc.moreDetailShop(shopIdList);
               if(ObjectUtils.isNotEmpty(shopIdList)){
                   for(CommonShopVO.ShopIdNameVO idNameVO:shopIdNameList){
                       for(DataNoticeVO.ListVO listVO:voList){
                           if(null != listVO.getShopId() && listVO.getShopId().equals(idNameVO.getId())){
                               listVO.setShopName(idNameVO.getShopName());
                           }
                       }
                   }
               }
            }
            for(DataNoticeVO.ListVO listVO:voList){
                if(null != listVO.getShopId() && null == listVO.getShopName()){
                    listVO.setShopName("未知店铺");
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),page.getTotal());
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addDataNotice(DataNoticeDTO.ETO dto) {
        if(NoticeScopeTypeEnum.全部商家.getCode().equals(dto.getScopeType())){
            DataNotice DataMessage = new DataNotice();
            BeanUtils.copyProperties(dto, DataMessage);
            repository.save(DataMessage);
        }else  if(NoticeScopeTypeEnum.ID商家.getCode().equals(dto.getScopeType())){
            if(ObjectUtils.isEmpty(dto.getMerchantShopList())){
                throw new BusinessException("指定接受商家ID数组不能为空");
            }
            DataNotice dataNotice = new DataNotice();
            BeanUtils.copyProperties(dto, dataNotice);
            repository.save(dataNotice);
            List<DataNoticeRecv> noticeRecvList = new ArrayList<>();
            for(MerchantShopDTO.ETO etoItem:dto.getMerchantShopList()){
                DataNoticeRecv dataNoticeRecv = new DataNoticeRecv();
                dataNoticeRecv.setNoticeId(dataNotice.getId());
                dataNoticeRecv.setState(NoticeReadStateEnum.未读.getCode());
                dataNoticeRecv.setShopId(etoItem.getShopId());
                dataNoticeRecv.setMerchantId(etoItem.getMerchantId());
                noticeRecvList.add(dataNoticeRecv);
            }
            dataNoticeRecvRepository.saveBatch(noticeRecvList);
        }
    }


    @Override
    public void deleteBatchDataNotice(DataNoticeDTO.IdListDTO dto) {
        if(ObjectUtils.isNotEmpty(dto.getIdList())){
            repository.removeByIds(dto.getIdList());
        }
    }


    @Override
    public DataNoticeVO.DetailVO detail(DataNoticeDTO.IdDTO dto) {
        DataNotice message = repository.getById(dto);
        if(ObjectUtils.isEmpty(message)){
            throw new BusinessException("没有数据");
        }
        DataNoticeVO.DetailVO detailVO = new DataNoticeVO.DetailVO();
        BeanUtils.copyProperties(message, detailVO);
       List<DataNoticeType> noticeTypeList =  dataNoticeTypeRepository.list();
       detailVO.setNoticeTypeList( ListUtil.listCover(DataNoticeTypeVO.DetailVO.class,noticeTypeList));
       return detailVO;
    }

}
