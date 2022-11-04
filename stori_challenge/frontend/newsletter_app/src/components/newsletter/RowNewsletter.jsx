import React, { useState, useEffect, useContext } from 'react';

import { NewsletterContext } from '../../contexts/newsletterContext';

export const RowNewsletter = ({ newsletter }) => {
  const [disabledBotton, setDisabledBotton] = useState(false);
  const [status, setEstatus] = useState("Sent");
  const { getNewsletter } = useContext(NewsletterContext);

  useEffect(() => {
    (newsletter.sentEstatus === 1) ? setEstatus("Sent"):setEstatus("Wait");

    if (newsletter.sentEstatus === 0 && newsletter.sentDate === null)
      setDisabledBotton(false);
    else 
      setDisabledBotton(true);
    
  }, [newsletter]);

  const handlerRow = () => {
    getNewsletter(newsletter);
  };
  return (
    <>
      <tr>
        <td>
          <button
            className="btn btn-primary btn-xs"
            title="Send"
            data-bs-toggle="modal"
            data-bs-target="#mailConfig"
            disabled={disabledBotton}
            onClick={handlerRow}
          >
            <span className="icon is-small">
              <i className="fa fa-send"></i>
            </span>
          </button>
        </td>
        <td>{newsletter.newsletterName}</td>
        <td>{newsletter.newsletterAttach}</td>
        <td>{newsletter.creationDate}</td>
        <td>{newsletter.sentDate}</td>
        <td>{status}</td>
      </tr>
    </>
  );
};
