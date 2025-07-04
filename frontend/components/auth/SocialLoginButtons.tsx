"use client";

// /components/auth/SocialLoginButtons.tsx
import Image from "next/image";
import React from "react";

// 직접 만든 로고 파일 경로 (PNG/WebP/JPG 등 가능)
const SOCIALS = [
  {
    name: "카카오",
    color: "bg-yellow-300 hover:bg-yellow-400",
    logo: "/social/kakao.webp",
    provider: "kakao",
  },
  {
    name: "구글",
    color: "bg-white border hover:bg-gray-100",
    logo: "/social/google.webp",
    provider: "google",
  },
  {
    name: "네이버",
    color: "bg-green-500 hover:bg-green-600",
    logo: "/social/naver.webp",
    provider: "naver",
  },
];

export default function SocialLoginButtons() {
  const handleSocial = (provider: string) => {
    window.location.href = `/api/auth/${provider}`;
  };

  return (
    <div className="flex justify-center space-x-4 my-2">
      {SOCIALS.map((s) => (
        <button
          key={s.provider}
          type="button"
          onClick={() => handleSocial(s.provider)}
          className={`min-w-12 min-h-12 w-12 h-12 flex items-center justify-center rounded-full shadow transition ${s.color} focus:ring-2 focus:ring-offset-2 focus:ring-yellow-300`}
          aria-label={`${s.name} 로그인`}
        >
          <Image
            src={s.logo}
            alt={`${s.name} 로그인 로고`}
            width={28}
            height={28}
            priority={s.provider === "google"}
            className="object-contain"
            draggable={false}
          />
        </button>
      ))}
    </div>
  );
}
