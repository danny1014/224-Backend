package com.pj224.app.main;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pj224.app.MemExecute;
import com.pj224.app.Result;
import com.pj224.app.dao.MainDAO;
import com.pj224.app.dto.MainDTO;

public class SearchHotController implements MemExecute{

	@Override
	public Result MemExecute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, ServletException {
		request.setCharacterEncoding("UTF-8");
	    MainDAO mainDAO = new MainDAO();
	    MainDTO mainDTO = new MainDTO();
	    Result result = new Result();

	    // 검색어 가져오기
	    String searchInput = request.getParameter("search");
	    System.out.println("검색어: " + searchInput);
	    mainDTO.setSearchInput(searchInput);
	    System.out.println(mainDTO);
	    List<MainDTO> searchhpResults;

	    // 검색어가 비어있지 않은 경우에만 검색 수행
	    if (searchInput != null && !searchInput.trim().isEmpty()) {
	        // 검색 결과 가져오기
	        searchhpResults = mainDAO.searchhpInfo(searchInput);
	        System.out.println("컨트롤러 검색어 : " + searchInput);
	        System.out.println("검색! \n" + searchhpResults);
	    } else {
	        // 검색어가 비어있을 경우 전체 리스트 가져오기
	        searchhpResults = mainDAO.searchhpList();
	        System.out.println(searchhpResults);
	        System.out.println("검색값 없음!");
	    }

	    // 검색 결과를 request에 저장
	    request.setAttribute("searchhpResults", searchhpResults);

	    // 결과 페이지 설정
	    result.setRedirect(false);
	    result.setPath(request.getContextPath() + "/main-search.jsp");
	    return result;
	}
	
}
