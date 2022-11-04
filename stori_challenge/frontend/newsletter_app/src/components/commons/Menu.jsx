import React from "react";
import { Link } from "react-router-dom";

export const Menu = ({ isUser }) => {
  if (isUser === 1)
    return (
      <ul className="navbar-nav me-auto mb-2 mb-lg-0">
        <li className="nav-item">
          <Link to="/user" className="nav-link active" aria-current="page">
            User
          </Link>
        </li>
      </ul>
    );
  else
    return (
      <>
        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
          <li className="nav-item">
            <Link to="/home" className="nav-link active" aria-current="page">
              <span>Home</span>
            </Link>
          </li>

          <li className="nav-item dropdown">
            <a
              className="nav-link dropdown-toggle"
              href="/#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Admin
            </a>
            <ul className="dropdown-menu">
              <li>
                <Link
                  to="/newsletter"
                  className="nav-link active"
                  aria-current="page"
                >
                  Create Newsletter
                </Link>
              </li>
              <li>
                <Link
                  to="/newsletterList"
                  className="nav-link active"
                  aria-current="page"
                >
                  Display Newsletter List
                </Link>
              </li>
              <li>
                <Link
                  to="/newsletterType"
                  className="nav-link active"
                  aria-current="page"
                >
                  Create Newsletter Type
                </Link>
              </li>
              <li>
                <hr className="dropdown-divider" />
              </li>
              <li>
                <Link
                  to="/statistics"
                  className="nav-link active"
                  aria-current="page"
                >
                  Display Statistics
                </Link>
              </li>
            </ul>
          </li>
        </ul>
      </>
    );
};
