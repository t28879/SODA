package com.mysite.Soda.Login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class KakaoService {

	public String getKakaoAccessToken(String code) {
		String access_Token="";
        String refresh_Token ="";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try{
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=dd08c5cbbd7e40e7d871075ed386c8a2"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:9597/SODA/kakao/callback"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
	}
	
	public String KakaoUser (String access_Token) {
	    
	    //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
	    //HashMap<String, Object> userInfo = new HashMap<>();
	    String reqURL = "https://kapi.kakao.com/v2/user/me";
	    String email = "";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        
	        //    요청에 필요한 Header에 포함될 내용
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("responseCode : " + responseCode);
	        
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String line = "";
	        String result = "";
	        
	        while ((line = br.readLine()) != null) {
	            result += line;
	        }
	        System.out.println("response body : " + result);
	        
	        JsonParser parser = new JsonParser();
	        JsonElement element = parser.parse(result);
	        
	        email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
	        System.out.println("Service-email : " + email);
	        
	        br.close();
	        
	        ;
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return email;
	}
	
	public void kakaoLogout(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/logout";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("Logout responseCode : " + responseCode);
	        
	        if (responseCode == 200) {
	            // 정상적으로 처리된 경우
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String result = "";
	            String line;
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println(result);
	        } else {
	            // 오류 발생 시 에러 응답을 출력
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            String errorResult = "";
	            String line;
	            
	            while ((line = br.readLine()) != null) {
	                errorResult += line;
	            }
	            System.out.println("Logout Error response: " + errorResult);
	        }
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
	
	public void unlink(String access_Token) {
	    String reqURL = "https://kapi.kakao.com/v1/user/unlink";
	    try {
	        URL url = new URL(reqURL);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "Bearer " + access_Token);
	        
	        int responseCode = conn.getResponseCode();
	        System.out.println("Unlink responseCode : " + responseCode);
	        
	        if (responseCode == 200) {
	            // 정상적으로 처리된 경우
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            String result = "";
	            String line;
	            
	            while ((line = br.readLine()) != null) {
	                result += line;
	            }
	            System.out.println(result);
	        } else {
	            // 오류 발생 시 에러 응답을 출력
	            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	            String errorResult = "";
	            String line;
	            
	            while ((line = br.readLine()) != null) {
	                errorResult += line;
	            }
	            System.out.println("Unlink Error response: " + errorResult);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
