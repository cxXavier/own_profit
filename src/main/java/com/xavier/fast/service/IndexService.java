package com.xavier.fast.service;

import com.xavier.fast.entity.StoreList;

import java.util.List;

/**
 * @author xavier
 */
public interface IndexService {

    List<StoreList> getUser(String in, String out);

    String insertRecord(String url,String remake);

}
