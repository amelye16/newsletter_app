import React from 'react';
import { useContext, useEffect } from 'react';
import {NewsletterContext} from '../../contexts/newsletterContext';

export const Statistics = () => {
  const { getStatistics, statisticsList } = useContext(NewsletterContext);
  useEffect(() => {
    getStatistics();
  }, []);

  return (
    <div>
      {statisticsList.map((index) => (
        <div key={index[0]}>{index}</div>
      ))}
    </div>
  );
};
