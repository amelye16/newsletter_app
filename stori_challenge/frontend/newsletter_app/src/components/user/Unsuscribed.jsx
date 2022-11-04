import React from "react";
import { useEffect, useContext, useState } from "react";
import { NewsletterContext } from "../../contexts/newsletterContext";

export const Unsuscribed = () => {
  const { suscribedList, getAllSuscribed, unsuscribedNewsletter } =
    useContext(NewsletterContext);

  const urlParams = new URLSearchParams(window.location.search);
  const [message, setMensaje] = useState("");

  let email = urlParams.get("email");

  useEffect(() => {
    getAllSuscribed(email);
  }, []);

  let unsuscribedList;
  const unsuscribedHandler = (e) => {
    let checks = document.querySelectorAll(".form-check-input");
    unsuscribedList = "";
    checks.forEach((e) => {
      if (e.checked === true) {
        unsuscribedList += e.value + " ";
      }
    });

    let paramUnsiscribed = new FormData();
    paramUnsiscribed.append("email", email);
    paramUnsiscribed.append("unsuscribeList", unsuscribedList);

    if (unsuscribedList.length > 0) 
      unsuscribedNewsletter(paramUnsiscribed);
    else {
      setMensaje("You don't have subscriptions selected");
    }   
  };
  return (
    <div className="card mt-3">
      <div className="card-header">Unsuscribed</div>
      <div className="card-body">
        <h5 className="card-title">We will miss you</h5>
        <p className="card-text">
          If you are sure to unsubscribe, you can to continue the process.
        </p>
        <p className="card-text">
          If you are subscribed, a list of options will be displayed.
        </p>

        {suscribedList.map((index) => (
          <div className="form-check" key={index[0]}>
            <input
              className="form-check-input"
              type="checkbox"
              value={index[0]}
              id="flexCheckDefault"
            />
            <label className="form-check-label" htmlFor="flexCheckDefault">
              {index[2]}
            </label>
          </div>
        ))}
        <button
          type="buttom"
          className="btn btn-primary  mt-3 mb-3"
          onClick={unsuscribedHandler}
        >
          Unsuscribed
        </button>

        <p className="card-text">{message}</p>
      </div>
    </div>
  );
};
