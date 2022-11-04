import React from "react";
import { Layout } from "../components/commons/Layout";
import { ButtonAddNewsletter } from "../components/newsletter/ButtonAddNewsletter";
import { TableNeswsletter } from "../components/newsletter/TableNeswsletter";
import { NewsletterContextProvider } from "../contexts/newsletterContext";
export const NewsletterList = () => {
  return (
    <div>
      <Layout>
        <NewsletterContextProvider>
          <div className="card">
            <div className="card-header">Newsletter List Pending</div>
            <div className="card-body">
              <ButtonAddNewsletter />
              <TableNeswsletter />
            </div>
          </div>
        </NewsletterContextProvider>
      </Layout>
    </div>
  );
};
