import React from 'react';
import { Link } from 'react-router-dom';
export const ButtonAddNewsletter = () => {
  return (
    <>
      <div className="container mt-3 mb-3">
        <Link
          to="/Newsletter"
          className="btn btn-primary btn-xs"
          aria-current="page"
          href="/"
        >
          <span className="icon is-small me-3">
            <i className="fa fa-plus"></i>
          </span>
          <span>Add newsletter Link</span>
        </Link>
      </div>
    </>
  );
};
