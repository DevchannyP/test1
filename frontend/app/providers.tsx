"use client";

import { queryClient } from "@/libs/react-query/queryClient";
import { Hydrate, QueryClientProvider } from "@tanstack/react-query";
import { ReactQueryDevtools } from "@tanstack/react-query-devtools";
import { ReactNode } from "react";

export default function Providers({
  children,
  dehydratedState,
}: {
  children: ReactNode;
  dehydratedState?: unknown;
}) {
  return (
    <QueryClientProvider client={queryClient}>
      <Hydrate state={dehydratedState}>
        {children}
        {process.env.NODE_ENV === "development" && (
          <ReactQueryDevtools initialIsOpen={false} />
        )}
      </Hydrate>
    </QueryClientProvider>
  );
}
