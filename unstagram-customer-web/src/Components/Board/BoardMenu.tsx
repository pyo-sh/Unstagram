import React from 'react';
import BoardMenuBox from 'Styles/Board/BoardMenuBox';
import { IconProp, menuIcon } from 'Styles/Main/HeaderMenuBox';

const iconSetting: IconProp = {
    label: "",
    className: "BoardMenu-Icon",
    fill:"#262626",
    width:24,
    height:24,
    viewBox:"0 0 48 48"
};

const BoardMenu: React.FC = () => {
    return (
        <BoardMenuBox>
            <button className="BoardMenu-Button">
                {menuIcon({...iconSetting, label:"Feed"})}
            </button>
            <button className="BoardMenu-Button">
                {menuIcon({...iconSetting, label:"Comment"})}
            </button>
            <button className="BoardMenu-Button">
                {menuIcon({...iconSetting, label:"Direct"})}
            </button>
            <button className="BoardMenu-Button BoardMenu-Book">
                {menuIcon({...iconSetting, label:"Book"})}
            </button>
        </BoardMenuBox>
    );
}

export default BoardMenu;