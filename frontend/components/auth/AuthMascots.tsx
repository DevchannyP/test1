"use client";

import Image from "next/image";

export default function AuthMascots() {
  return (
    <div className="w-full flex justify-center mb-4">
      <Image
        src="/social/mascots/auth-mascots.webp" // ✅ public 경로 기준
        alt="지방청년 마스코트 4종"
        width={420}
        height={210}
        className="rounded-2xl shadow-lg"
        priority
        sizes="(max-width: 480px) 100vw, 420px"
        // ❌ placeholder="blur" 제거 (public 경로는 지원 안 됨)
      />
    </div>
  );
}
