import React from 'react';
import LayoutBox from 'Styles/Pages/LayoutBox';
import Header from 'Components/Main/Header';

const Layout: React.FC = ({ children }) => {
  return (
    <LayoutBox>
      <Header/>
      {children}
    </LayoutBox>
  );
}

export default Layout;
