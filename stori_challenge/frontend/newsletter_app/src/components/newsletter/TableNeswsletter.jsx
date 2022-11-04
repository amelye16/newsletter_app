import React, { useContext, useEffect } from "react";
import { RowNewsletter } from "./RowNewsletter";
import { Modal } from "../commons/Modal";
import { NewsletterContext } from "../../contexts/newsletterContext";

export const TableNeswsletter = () => {
  const { newsletterList, getNewsletters } = useContext(NewsletterContext);

  useEffect(() => {
    getNewsletters();
  }, []);
  useEffect(() => {}, [newsletterList]);

  if (newsletterList.length === 0)
    return (
      <center>
        <p>No records for Newsletter List</p>
      </center>
    );
  return (
    <>
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Actions</th>
            <th>Name</th>
            <th>Attach</th>
            <th>Creation Date</th>
            <th>Sent Date</th>
            <th>Estatus</th>
          </tr>
        </thead>
        <tbody>
          {newsletterList.map((newsletter) => (
            <RowNewsletter
              newsletter={newsletter}
              key={newsletter.newsletterId}
            />
          ))}
        </tbody>
      </table>
      <Modal title="Newsletter List- Email configuration" id="mailConfig" />
    </>
  );
};
