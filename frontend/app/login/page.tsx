"use client";

import AuthMascots from "@/components/auth/AuthMascots";
import LoginForm from "@/components/auth/LoginForm";
import SocialLoginButtons from "@/components/auth/SocialLoginButtons";
import Link from "next/link";

export default function LoginPage() {
  return (
    <main className="min-h-screen flex flex-col items-center bg-yellow-100">
      <section className="w-full max-w-md bg-white shadow-xl rounded-b-3xl px-6 py-8 flex flex-col items-center mt-0">
        {/* 마스코트 */}
        <AuthMascots />

        {/* 로그인 폼 */}
        <LoginForm />

        {/* 소셜 구분선 */}
        <div className="flex items-center my-4 w-full">
          <span className="flex-1 h-px bg-gray-300" />
          <span className="px-3 text-gray-500 text-sm">or</span>
          <span className="flex-1 h-px bg-gray-300" />
        </div>

        {/* 소셜 로그인 버튼 */}
        <SocialLoginButtons />

        {/* 회원가입 안내 */}
        <div className="text-sm mt-4 text-center text-gray-500">
          계정이 없으신가요?{" "}
          <Link href="/auth/signup" className="text-blue-500 hover:underline">
            회원가입
          </Link>
        </div>
      </section>
    </main>
  );
}
