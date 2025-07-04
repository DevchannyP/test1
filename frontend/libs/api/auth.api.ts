// /libs/api/auth.api.ts

// [실무] 서버 응답 데이터 타입 예시
export interface LoginResponse {
  user: {
    id: number;
    username: string;
    email: string;
    nickname?: string;
    // ... 필요한 필드 추가
  };
  // accessToken?: string; // 쿠키 사용시 생략 가능
  [key: string]: any; // 확장성
}

export interface ApiError {
  code?: string;
  message: string;
}

// 이메일 로그인 API (fetch + credentials + 예외/타입 보강)
export async function loginWithEmail(
  username: string,
  password: string
): Promise<LoginResponse> {
  try {
    const res = await fetch("/api/auth/email-login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
      credentials: "include", // 서버에서 HttpOnly 쿠키로 토큰 발급 (JS 접근X)
    });

    // 서버 에러 처리
    if (!res.ok) {
      let errMsg = "로그인 실패";
      try {
        const err: ApiError = await res.json();
        errMsg = err.message || errMsg;
      } catch {
        // 응답이 json이 아니거나 네트워크 오류
      }
      throw new Error(errMsg);
    }

    // 정상 응답
    return await res.json();
  } catch (e: any) {
    // 네트워크 오류, 서버 장애 등 추가 처리
    throw new Error(e.message || "서버 연결 실패");
  }
}
