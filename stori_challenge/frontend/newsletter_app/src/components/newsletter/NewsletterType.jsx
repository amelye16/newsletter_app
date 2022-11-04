import React from "react";
import { useState, useEffect, useContext } from "react";
import { NewsletterContext } from "../../contexts/newsletterContext";

export const NewsletterType = () => {
  const { addNewsletterType } = useContext(NewsletterContext);
  const [message, setMensaje] = useState("");
  const [newsletterType, setNesletterType] = useState({
    newsletterTypeName: "",
    newsletterTypeDesc: "",
  });
  const { newsletterTypeName, newsletterTypeDesc } = newsletterType;
  const handlerType = ({ target }) => {
    setNesletterType({
      ...newsletterType,
      [target.name]: target.value,
    });
  };

  useEffect(() => {
    setMensaje("");
  }, [newsletterType]);

  const handlerNesletterType = (e) => {
    e.preventDefault();
    if (newsletterTypeName === "") {
      setMensaje("Newsletter Type Name is required");
      return;
    }
    if (newsletterTypeDesc === "") {
      setMensaje("Newsletter Type Description is required");
      return;
    }
    if (message !== "") {
      return;
    }
    addNewsletterType(JSON.stringify(newsletterType));
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="col-6 offset-3">
            <h5 className="mb-3"> Create Newsletter Type </h5>
            {message && (
              <div className="alert alert-danger mt-3" role="alert">
                {message}
              </div>
            )}
            <div className="form-group ">
              <label htmlFor="newsletterTypeName" className="form-label ">
                Newsletter type name
              </label>
              <input
                className="form-control mb-3"
                id="newsletterTypeName"
                name="newsletterTypeName"
                placeholder="Introduce newsletter type name"
                type="text"
                value={newsletterTypeName}
                onChange={handlerType}
              />
            </div>
            <div className="form-group ">
              <label htmlFor="newsletterTypeDesc" className="form-label ">
                Newsletter type description
              </label>
              <input
                className="form-control mb-3"
                id="newsletterTypeDesc"
                name="newsletterTypeDesc"
                placeholder="Introduce newsletter type description"
                type="text"
                value={newsletterTypeDesc}
                onChange={handlerType}
              />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-4 offset-3">
            <button
              type="buttom"
              className="btn btn-primary  mt-3 mb-5"
              onClick={handlerNesletterType}
            >
              Save Newsletter Type
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
