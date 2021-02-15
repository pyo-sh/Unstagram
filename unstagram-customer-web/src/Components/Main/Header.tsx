import React from 'react';
import HeaderBox from 'Styles/Main/HeaderBox';
import HeaderMenu from './HeaderMenu';

const Header: React.FC = () => {
  return (
    <HeaderBox>
      <div className="Header-Wrapper">
        <div className="Header-Logo Unstagram-Title">Unstagram</div>
        <div className="Header-Search">
          <img className="Header-Search-Icon" src="search.png"/>
          <input
          className="Header-Search-Input"
          placeholder="검색"/>
        </div>
        <div className="Header-Menu-Wrapper">
          <HeaderMenu/>
        </div>
      </div>
    </HeaderBox>
  );
}

export default Header;
