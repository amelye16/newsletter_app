import React, { useState } from "react";
import { Menu } from "./Menu";

export const Header = (props) => {
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);
  const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light rounded">
      <a className="navbar-brand text-info font-weight-bolder" href="/">
        <img
          src={require("../../assets/images/logo.png")}
          alt="image_bank"
          width="100"
          height="30"
          className="ms-5"
        />
      </a>
      <button
        className="custom-toggler navbar-toggler me-3"
        type="button"
        data-toggle="collapse"
        data-target="#navbarCollapse"
        aria-controls="navbarCollapse"
        aria-expanded={!isNavCollapsed ? true : false}
        aria-label="Toggle navigation"
        onClick={handleNavCollapse}
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div
        className={`${isNavCollapsed ? "collapse" : ""} navbar-collapse`}
        id="navbarCollapse"
      >
        <Menu isUser={props.isUser} />
      </div>
    </nav>
  );
};
