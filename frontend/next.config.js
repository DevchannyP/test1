/**
 * @type {import('next').NextConfig}
 * → TypeScript 기반의 자동 완성 및 타입 검사 지원을 위한 주석입니다.
 * → 이 설정을 통해 VSCode 등에서 nextConfig 객체에 사용할 수 있는 옵션을 자동으로 추천해줍니다.
 */
const nextConfig = {
  // ⚠️ 개발 중 오류를 더 잘 찾을 수 있도록 React의 Strict Mode를 활성화합니다.
  // → 컴포넌트가 두 번 렌더링되는 것처럼 보일 수 있으나, 이는 의도된 동작으로
  //    사이드이펙트(부작용) 검출에 도움을 줍니다.
  reactStrictMode: true,

  // ⚡ Next.js의 swc(Speedy Web Compiler)를 사용하여 빌드를 빠르게 하고,
  //    JS/TS 코드를 더 작고 효율적으로 압축(minify)합니다.
  swcMinify: true,

  // 🧪 실험적 기능을 사용하는 설정입니다.
  experimental: {
    // 📁 App Router를 사용하기 위해 필요한 설정입니다.
    // → `app/` 디렉토리를 기반으로 한 라우팅(App Router)을 사용할 수 있게 합니다.
    // → 만약 기존의 `pages/` 디렉토리를 사용하는 경우에는 필요 없습니다.
    appDir: true,
  },
};

// 🔁 위에서 설정한 nextConfig 객체를 외부에서 사용할 수 있도록 내보냅니다.
// → Next.js는 프로젝트 루트의 `next.config.js` 파일에서 이 객체를 자동으로 읽습니다.
module.exports = nextConfig;
