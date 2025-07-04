"use client";

import { useState } from "react";
import { useAuthStore } from "@/store/authStore";
import { loginWithEmail } from "@/libs/api/auth.api";
import { useMutation } from "@tanstack/react-query";
import Link from "next/link";

// 로그인 폼 컴포넌트 (실무 최적화)
export default function LoginForm() {
  const { setUser } = useAuthStore();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [showPw, setShowPw] = useState(false);
  const [error, setError] = useState("");

  // 로그인 요청 뮤테이션
  const loginMutation = useMutation({
    mutationFn: () => loginWithEmail(email, password),
    onSuccess: (res) => {
      setUser(res.user);
      // JWT 토큰 로컬스토리지/쿠키 저장 (실무 예시)
      localStorage.setItem("accessToken", res.accessToken);
      window.location.href = "/dashboard"; // 메인 이동
    },
    onError: (err: any) => {
      // 보안상 에러 메시지는 단순화, 상세 로깅은 콘솔/모니터링으로 분리
      setError(
        err?.response?.data?.message ||
          "이메일 또는 비밀번호가 올바르지 않습니다."
      );
    },
  });

  // 입력 핸들러, 엔터 키 로그인 지원
  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter" && !loginMutation.isLoading) {
      loginMutation.mutate();
    }
  };

  return (
    <form
      className="w-full max-w-xs mx-auto flex flex-col space-y-3"
      autoComplete="on"
      onSubmit={(e) => {
        e.preventDefault();
        if (!loginMutation.isLoading) loginMutation.mutate();
      }}
    >
      <input
        className="border rounded-lg px-4 py-3 mb-2 focus:outline-none focus:ring-2 focus:ring-yellow-300"
        type="text"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        placeholder="아이디를 입력하세요"
        autoComplete="username"
        aria-label="이메일"
        required
        onKeyDown={handleKeyDown}
      />
      <div className="relative">
        <input
          className="border rounded-lg px-4 py-3 mb-2 w-full focus:outline-none focus:ring-2 focus:ring-yellow-300"
          type={showPw ? "text" : "password"}
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="비밀번호를 입력하세요"
          autoComplete="current-password"
          aria-label="비밀번호"
          required
          onKeyDown={handleKeyDown}
        />
        <button
          type="button"
          tabIndex={-1}
          className="absolute right-2 top-3 text-xs text-gray-500"
          onClick={() => setShowPw((v) => !v)}
          aria-label={showPw ? "비밀번호 숨기기" : "비밀번호 보이기"}
        >
          {showPw ? "숨김" : "보기"}
        </button>
      </div>
      {error && <div className="text-red-500 text-sm">{error}</div>}
      <button
        type="submit"
        className="bg-yellow-400 hover:bg-yellow-500 text-white font-bold py-2 rounded-lg mt-1 disabled:opacity-60"
        disabled={loginMutation.isLoading}
      >
        {loginMutation.isLoading ? "로그인 중..." : "로그인"}
      </button>
      <div className="flex justify-between text-xs text-gray-600 pt-2">
        <Link href="/auth/find-id" className="hover:underline">
          아이디 찾기
        </Link>
        <Link href="/auth/find-password" className="hover:underline">
          비밀번호 찾기
        </Link>
      </div>
    </form>
  );
}
