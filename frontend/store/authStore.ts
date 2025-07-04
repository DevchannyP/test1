import { create } from "zustand";

// 1. 사용자 정보 타입 정의(필요시 확장)
export interface User {
  id: number;
  email: string;
  nickname: string;
  // role?: string;  // 예시: 관리자 등급 등 추가 가능
}

// 2. 인증 상태 구조 정의
export interface AuthState {
  user: User | null;
  setUser: (user: User | null) => void;
  logout: () => void; // 로그아웃 액션 추가
}

// 3. Zustand 스토어 생성
export const useAuthStore = create<AuthState>((set) => ({
  user: null, // 로그인 사용자 정보
  setUser: (user) => set({ user }),
  logout: () => set({ user: null }), // 로그아웃 시 null 처리
}));

/* 
  사용 예시
  -----------
  const { user, setUser, logout } = useAuthStore();
  setUser({ id: 1, email: "a@b.com", nickname: "찬희" });
  logout();
*/
