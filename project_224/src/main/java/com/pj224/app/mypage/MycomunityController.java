package com.pj224.app.mypage;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pj224.app.MemExecute;
import com.pj224.app.Result;
import com.pj224.app.dao.MypageDAO;
import com.pj224.app.dto.CommunityDTO;
import com.pj224.app.dto.HotplaceDTO;
import com.pj224.app.dto.MemberDTO;

public class MycomunityController implements MemExecute{

	@Override
	public Result MemExecute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServerException, ServletException {
		System.out.println("커뮤니티 컨트롤러 실행");
		HttpSession session = request.getSession(false);
		
		CommunityDTO communityDTO = new CommunityDTO();
		MypageDAO mypageDAO = new MypageDAO();
		Result result = new Result();
		
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		
		int membersessionNum = memberDTO.getMemberNumber();
		System.out.println("membernumber 들어왔니");
		System.out.println(membersessionNum);
		
		communityDTO.setBoardNumber(membersessionNum);
		System.out.println(communityDTO);
		
		List<CommunityDTO> mywritepage = mypageDAO.mywritelist(membersessionNum);
		System.out.println("mywritelist 들어왔니");
		System.out.println("Community 값 들어왔니");
		System.out.println(mywritepage);
		
		request.setAttribute("mywritepage",mywritepage);
		
		request.getRequestDispatcher(request.getContextPath())
		return null;
	}

}
