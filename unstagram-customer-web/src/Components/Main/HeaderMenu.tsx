import React from 'react';
import HeaderMenuBox, { IconProp, menuIcon } from 'Styles/Main/HeaderMenuBox';

const iconSetting: IconProp = {
    label: "",
    className: "HeaderMenu-Icon",
    fill:"#262626", 
    width:22,
    height:22,
    viewBox:"0 0 48 48"
};

const HeaderMenu: React.FC = () => {
  return (
    <HeaderMenuBox>
        {menuIcon({...iconSetting, label:"Home"})}
        {menuIcon({...iconSetting, label:"Direct"})}
        {menuIcon({...iconSetting, label:"Find"})}
        {menuIcon({...iconSetting, label:"Feed"})}
    </HeaderMenuBox>
  );
}

export default HeaderMenu;
