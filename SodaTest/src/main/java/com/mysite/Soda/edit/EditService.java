package com.mysite.Soda.edit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.Soda.DTO.CompanyDTO;
import com.mysite.Soda.DTO.JoinMember;
import com.mysite.Soda.Join.JoinRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditService {
	
	private final EditRepository editRepository;
	private final JoinRepository joinRepository;
	
	public boolean changeName(Integer userNum, String userName) {
		Optional<EditMember> memberId = editRepository.findById(userNum);
		if(memberId.isPresent()) {
			EditMember editMember = memberId.get();
			editMember.setName(userName);
			editRepository.save(editMember);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean changeJobName(Integer userNum, String jobName) {
		Optional<EditMember> memberId = editRepository.findById(userNum);
		if(memberId.isPresent()) {
			EditMember editMember = memberId.get();
			editMember.setJobName(jobName);
			editRepository.save(editMember);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean changePhoneNum(Integer userNum, String phoneNum) {
		Optional<EditMember> memberId = editRepository.findById(userNum);
		if(memberId.isPresent()) {
			EditMember editMember = memberId.get();
			editMember.setPhoneNum(phoneNum);
			editRepository.save(editMember);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean changePW(Integer userNum, String currentPW, String newPW) {
		Optional<EditMember> memberId = editRepository.findById(userNum);
		EditMember memberPw = editRepository.findByPw(currentPW);
		if(memberPw != null) {
			String dbPw = memberPw.getPw();
			System.out.println("service: " + dbPw);
			System.out.println("service: " + currentPW);
			System.out.println("service: " + newPW);
			if(memberId.isPresent()) {
				if(dbPw.equals(currentPW)) {
					EditMember editMember = memberId.get();
					editMember.setPw(newPW);
					editRepository.save(editMember);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String findComName(Integer userNum) {
		Optional<JoinMember> joinMember = joinRepository.findById(userNum);
		if(joinMember != null) {
			JoinMember company = joinMember.get();
			CompanyDTO comId = company.getCompanyId();
			System.out.println("service: " + userNum);
			System.out.println("service: " + company);
			System.out.println("service: " + comId);
			String comName = comId.getName();
			return comName;
		}
		return null;
	}
	
	public String editImage(Integer userNum, MultipartFile file) {
		try {
			Optional<EditMember> memberId = editRepository.findById(userNum);
			EditMember Id = memberId.get();
			int cnt = Id.getMemberId();
			
			String uploadPath = "src/main/resources/static/upload/upload_";
			byte[] bytes = file.getBytes();
			Path path = Paths.get(uploadPath + cnt + "_" +file.getOriginalFilename());
			Files.write(path, bytes);
			
			if(memberId.isPresent()) {
				EditMember editImg = memberId.get();
				editImg.setProfileImage("upload/upload" + "_"+ cnt + "_" + file.getOriginalFilename());
				editRepository.save(editImg);
				return editImg.getProfileImage();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
