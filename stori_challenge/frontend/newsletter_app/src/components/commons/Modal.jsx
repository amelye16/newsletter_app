import React from "react";
import { NewsletterContext } from "../../contexts/newsletterContext";
import { useState, useContext, useRef, useEffect } from "react";
import { validateEmailConfig } from "../../helpers/validateEmailConfig";

export const Modal = ({ title, id }) => {
  const { currentData, addEmailUser } = useContext(NewsletterContext);
  const [disabledUser, setDisabledUser] = useState(false);
  const [disabledFile, setDisabledFile] = useState(true);
  const [sendList, setSendList] = useState(0);
  const [message, setMensaje] = useState("");
  const [file, setFile] = useState(null);
  const inputRef = useRef(null);
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState({
    userName: "",
    userEmail: "",
  });
  const { userName, userEmail } = user;

  useEffect(() => {
    setMensaje("");
  }, [file, user]);

  const handlerModal = ({ target }) => {
    setUser({
      ...user,
      [target.name]: target.value,
    });
  };

  const handlerSendList = () => {   
    if (sendList === 0) {
      setSendList(1);
      setDisabledFile(false);
      setDisabledUser(true);
    } else {
      setSendList(0);
      setDisabledFile(true);
      setDisabledUser(false);
    }
  };

  const handlerFile = (e) => {
    if (e.target.files.length < 1) {
      setMensaje("You need to provide a file");
      return;
    } else {
      setFile(e.target.files[0]);
    }
  };

  const closeModal = (e) => {
    setUser({
      userName: "",
      userEmail: "",
    });
    setFile(null);
  };
  const handlerSendModal = (e) => {
    e.preventDefault();

    let {
      newsletterId,
      createDate,
      newsletterAttach,
      newsletterName,
      newsletterDescription,
      newsletterTypeId,
      sentDate,
      sentEstatus,
    } = currentData;

    let valueMessage = validateEmailConfig(user, sendList, file);
    setMensaje(valueMessage);

    if (valueMessage !== "") {
      return;
    }

    const configEmail = {
      newsletterId: newsletterId,
      sendList: sendList,
      name: userName,
      email: userEmail,
    };
    
    const newsletterDetails = {
      newsletterId: newsletterId,
      createDate: createDate,
      newsletterAttach: newsletterAttach,
      newsletterName: newsletterName,
      newsletterDescription: newsletterDescription,
      newsletterTypeId: newsletterTypeId,
      sentDate: sentDate,
      sentEstatus: sentEstatus,
      sendNow: 1,
    };

    let newsletterParams = new FormData();
    newsletterParams.append("configEmail", JSON.stringify(configEmail));
    newsletterParams.append(
      "newsletterDetails",
      JSON.stringify(newsletterDetails)
    );

    if (sendList === 1) 
      newsletterParams.append("file", file);

    addEmailUser(newsletterParams, sendList, setLoading);
  };

  return (
    <>
      <div className="modal fade " tabIndex="-1" id={id} name={id}>
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title">{title}</h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
                onClick={closeModal}
              ></button>
            </div>
            <div className="modal-body">
              {message && <p className="alert alert-danger mt-3">{message}</p>}
              <div className="row">
                <div className="form-group ">
                  <label htmlFor="userName" className="form-label ">
                    Name
                  </label>
                  <input
                    className="form-control mb-3"
                    id="userName"
                    name="userName"
                    placeholder="Introduce name"
                    type="text"
                    value={userName}
                    disabled={disabledUser}
                    onChange={handlerModal}
                  />
                </div>
                <div className="form-group mb-3">
                  <label htmlFor="userEmail" className="form-label">
                    Email
                  </label>
                  <input
                    className="form-control"
                    id="userEmail"
                    name="userEmail"
                    placeholder="Introduce email"
                    type="email"
                    disabled={disabledUser}
                    value={userEmail}
                    onChange={handlerModal}
                  />
                </div>
                <div className="mb-3 ms-3 form-check">
                  <input
                    type="checkbox"
                    className="form-check-input"
                    id="checkList"
                    onClick={handlerSendList}
                  />
                  <label className="form-check-label" htmlFor="checkList">
                    Send user list
                  </label>
                </div>
                <div className="form-group mb-3">
                  <label htmlFor="newsletterFile" className="form-label">
                    Upload a CSV File
                  </label>
                  <input
                    className="form-control"
                    id="file"
                    name="file"
                    type="file"
                    onChange={handlerFile}
                    disabled={disabledFile}
                    ref={inputRef}
                  />
                </div>
              </div>
            </div>
            <div className="modal-footer">
              {loading ? (
                <div>
                  Loading{" "}
                  <img
                    src={require("../../assets/images/loading.gif")}
                    alt="loading..."
                    width="100"
                    height="70"
                  />
                </div>
              ) : (
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handlerSendModal}
                  disabled={loading}
                >
                  Send Now
                </button>
              )}

              <button
                type="button"
                className="btn btn-secondary"
                data-bs-dismiss="modal"
                onClick={closeModal}
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
