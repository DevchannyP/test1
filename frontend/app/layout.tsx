import type { Metadata } from "next";
import Providers from "./providers"; // ✅ React Query Provider

// ✅ 메타데이터 정의 (SEO 개선)
export const metadata: Metadata = {
  title: "지방청년 플랫폼",
  description: "정책 추천과 커뮤니티를 지원하는 지역 정착 플랫폼",
  keywords: [
    "지방청년",
    "정책 추천",
    "지역 커뮤니티",
    "Next.js",
    "Spring Boot",
  ],
  icons: {
    icon: "/favicon.ico",
  },
};

// ✅ 루트 레이아웃 컴포넌트
export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="ko">
      <body>
        <Providers>{children}</Providers>
      </body>
    </html>
  );
}
