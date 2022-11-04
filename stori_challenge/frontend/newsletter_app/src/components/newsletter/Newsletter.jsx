import React from 'react';
import { useState, useEffect, useContext } from 'react';
import { validateFields } from '../../helpers/validateFields';
import { NewsletterContext } from '../../contexts/newsletterContext';
import { Modal } from '../commons/Modal';

export const Newsletter = () => {
  const { newsletterTypeList, addNewsletter, getAllNewsletterType } =
    useContext(NewsletterContext);

  const [newsletter, setNewsletter] = useState({
    newsletterName: "",
    newsletterDescription: "",
    newsletterType: "",
  });
  const [loading, setLoading] = useState(false);
  const { newsletterName, newsletterDescription } = newsletter;
  const [message, setMensaje] = useState("");
  const [sendDisabled, setSendDisabled] = useState(true);
  const [file, setFile] = useState(null);

  useEffect(() => {
    setMensaje("");
  }, [newsletter, file]);

  useEffect(() => {
    getAllNewsletterType();
  }, []);

  const handlerNewsletter = ({ target }) => {
    setNewsletter({
      ...newsletter,
      [target.name]: target.value,
    });
  };

  const handlerSelect = (e) => {
    newsletter.newsletterType = e.target.value;
  };

  const handlerFile = (e) => {
    if (e.target.files.length < 1) {
      setMensaje("You need to provide a file");
      return;
    } else {
      setFile(e.target.files[0]);
    }
  };

  const handlerForm = (e) => {
    e.preventDefault();

    let valueMessage = validateFields(newsletter, file);
    setMensaje(valueMessage);

    if (valueMessage !== "") {
      return;
    }

    let paramNewsletter = new FormData();
    paramNewsletter.append("newsletterName", newsletter.newsletterName);
    paramNewsletter.append(
      "newsletterDescription",
      newsletter.newsletterDescription
    );
    paramNewsletter.append("newsletterType", newsletter.newsletterType);
    paramNewsletter.append("file", file);

    setSendDisabled(false);
    addNewsletter(paramNewsletter, setLoading);
  };
  
  const handlerClean = () => {
    setNewsletter({
      newsletterName: "",
      newsletterDescription: "",
      newsletterType: "",
    });
    setFile(null);
  };

  return (
    <div>
      <div className="container">
        <div className="row">
          <div className="col-6 offset-3">
            <h5 className="mb-3"> Newsletter Details </h5>
            {message && (
              <div className="alert alert-danger mt-3" role="alert">
                {message}
              </div>
            )}
            <div className="form-group ">
              <label htmlFor="newsletterName" className="form-label ">
                Newsletter Title
              </label>
              <input
                className="form-control mb-3"
                id="newsletterName"
                name="newsletterName"
                placeholder="Introduce newsletter title"
                type="text"
                value={newsletterName}
                onChange={handlerNewsletter}
              />
            </div>
            <div className="form-group ">
              <label htmlFor="newsletterDescrip" className="form-label ">
                Newsletter Description
              </label>
              <input
                className="form-control mb-3"
                id="newsletterDescription"
                name="newsletterDescription"
                placeholder="Introduce newsletter Description"
                type="text"
                value={newsletterDescription}
                onChange={handlerNewsletter}
              />
            </div>
            <div className="col-md-4">
              <label htmlFor="newsletterType" className="form-label">
                Newsletter Type
              </label>
              <select
                id="newsletterType"
                className="form-select  mb-3"
                defaultValue={"DEFAULT"}
                onChange={handlerSelect}
              >
                <option value="DEFAULT" disabled>
                  Select newsletter type
                </option>
                {newsletterTypeList.map((type) => (
                  <option
                    value={type.newsletterTypeId}
                    key={type.newsletterTypeId}
                  >
                    {type.newsletterTypeName}
                  </option>
                ))}
              </select>
            </div>

            <div className="form-group mb-3">
              <label htmlFor="newsletterFile" className="form-label">
                Upload Newsletter File (.png/.pdf)
              </label>
              <input
                className="form-control"
                id="file"
                name="file"
                type="file"
                onChange={handlerFile}
              />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-2 offset-3">
            <button
              type="buttom"
              className="btn btn-primary  mt-3 mb-5"
              onClick={handlerForm}
              disabled={loading}
            >
              Save Newsletter
            </button>
          </div>

          <div className="col-2 ">
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
                type="buttom"
                data-bs-toggle="modal"
                data-bs-target="#sendNesletter"
                className="btn btn-primary  mt-3 mb-5"
                disabled={sendDisabled}
              >
                Send Newsletter
              </button>
            )}
          </div>
          <div className="col-2 ">
            <button
              type="buttom"
              className="btn btn-primary  mt-3 mb-5"
              onClick={handlerClean}
            >
              Clean data
            </button>
          </div>
        </div>
      </div>
      <Modal title="Newsletter- Email configuration" id="sendNesletter" />
    </div>
  );
};
