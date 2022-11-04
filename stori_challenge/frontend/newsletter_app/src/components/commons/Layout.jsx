import React from "react";
import { Header } from "./Header";

export const Layout = (props) => {
  return (
    <>
      <Header />
      <div className="container mt-3">{props.children}</div>
    </>
  );
};
