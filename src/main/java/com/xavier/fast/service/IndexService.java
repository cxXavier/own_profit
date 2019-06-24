package com.xavier.fast.service;

import java.util.List;

/**
 * @author xavier
 */
public interface IndexService {

    List<StoreList> getUser(String in, String out);

    String insertRecord(String url,String remake);

}
