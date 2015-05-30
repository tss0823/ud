/*
 * 
 * 
 * 
 * 
 */

package com.usefullc.ud.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.usefullc.platform.common.web.Pagination;
import com.usefullc.platform.service.AbstractBaseService;
import com.usefullc.ud.dao.IEnumDataItemDao;
import com.usefullc.ud.domain.EnumDataItem;
import com.usefullc.ud.service.IEnumDataItemService;

@Service
public class EnumDataItemServiceImpl extends AbstractBaseService implements IEnumDataItemService {

    @Autowired
    private IEnumDataItemDao enumDataItemDao;

    @Override
    public EnumDataItem getEnumDataItem(Long id) {
        return enumDataItemDao.getEnumDataItem(id);
    }

    @Override
    public List<EnumDataItem> getEnumDataItemList(Map<String, Object> queryMap) {
        return enumDataItemDao.getEnumDataItemList(queryMap);
    }

    @Override
    public Pagination<EnumDataItem> getEnumDataItemListPage(Map<String, Object> queryMap) {
        return enumDataItemDao.getEnumDataItemListPage(queryMap);
    }

    @Override
    public void insertEnumDataItem(EnumDataItem enumDataItem) {
        enumDataItemDao.insertEnumDataItem(enumDataItem);
    }

    @Override
    public void updateEnumDataItem(EnumDataItem enumDataItem) {
        enumDataItemDao.updateEnumDataItem(enumDataItem);
    }

    @Override
    public void deleteEnumDataItem(Long id) {
        enumDataItemDao.deleteEnumDataItem(id);
    }

    @Override
    public void updateEnumDataItem(Long parentId, String[] value, String[] text, String[] extText) {
        // 删除
        this.sqlSession.delete("EnumDataItemMapper.deleteEnumDataItemByParentId", parentId);

        // 新增
        for (int i = 0; i < value.length; i++) {
            if (StringUtils.isEmpty(value[i])) {
                continue;
            }
            EnumDataItem dataItem = new EnumDataItem();
            dataItem.setParentId(parentId);
            dataItem.setValue(value[i]);
            dataItem.setText(text[i]);
            dataItem.setExtText(extText[i]);
            enumDataItemDao.insertEnumDataItem(dataItem);
        }

    }

}
