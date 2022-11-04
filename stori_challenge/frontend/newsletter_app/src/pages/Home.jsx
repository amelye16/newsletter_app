import React from "react";
import { Layout } from "../components/commons/Layout";
export const Home = () => {
  return (
    <div>
      <Layout>
        <div className="card col-8 offset-2">
          <div className="card-header">Home - Stori Chalenge </div>
          <div className="card-body">
            <p> That is a newsletter sending app. </p>
            <p> <b>Delivery and code requirements</b> </p>
            <ol>
              <li>Admin user can upload a pdf or png image (the newsletter)</li>
              <li>
                Admin user can submit an email list of recipients of the
                newsletter
              </li>
              <li>Admin user can add a single email to the recipient list</li>
              <li>
                Admin user can click a button to trigger the newsletter
                submission
              </li>
              <li>PDF of png document should be attached to the email</li>
              <li>Admin user can upload a pdf or png image (the newsletter)</li>
              <li>
                Recipient users can click a "unsubscribe" link contained in the
                email, the user should not receive any more emails
              </li>
            </ol>

            <p> <b>Bonus points</b> </p>
            <ol>
              <li>Email is personalized and using html format</li>
              <li>
                Recipient user can opt for only unsubscribe from specific
                newsletters
              </li>
              <li>
                A statistics dashboard is provided (number of sent mails, number
                of recipients, etc.)
              </li>
              <li>Newsletter sending can be scheduled</li>{" "}
            </ol>
          </div>
        </div>
      </Layout>
    </div>
  );
};
