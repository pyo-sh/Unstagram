import React from 'react';
import ProfileCategoryMenuBox, { categoryIcon } from 'Styles/Profile/ProfileCategoryMenuBox';
import { checkWidthDevice } from 'Contexts/WindowSize';

interface PropType {
    category: string,
    menuOnClick: Function
}
const ProfileCategoryMenu: React.FC<PropType> = ({ category, menuOnClick }) => {
    const isDesktop = checkWidthDevice();
    const selectedColor = (isDesktop ? "#262626" : "#0095f6");

    return (
        <ProfileCategoryMenuBox>
            <button
                className={"ProfileCategoryMenu-Button"
                + (category === "Board" ? ' ProfileCategoryMenu-Selected' : '')}
                onClick={menuOnClick("Board")}
                >
                {categoryIcon({
                    label: "Board",
                    className: "ProfileCategoryMenu-Icon",
                    fill: category === "Board" ? selectedColor : "#8e8e8e",
                    width: isDesktop ? 12 : 24,
                    height: isDesktop ? 12 : 24,
                    viewBox: "0 0 48 48"
                })}
                {isDesktop ? "게시물" : ""}
            </button>
            <button
                className={"ProfileCategoryMenu-Button"
                + (category === "Saved" ? ' ProfileCategoryMenu-Selected' : '')}
                onClick={menuOnClick("Saved")}
                >
                {categoryIcon({
                    label: "Saved",
                    className: "ProfileCategoryMenu-Icon",
                    fill: category === "Saved" ? selectedColor : "#8e8e8e",
                    width: isDesktop ? 12 : 24,
                    height: isDesktop ? 12 : 24,
                    viewBox: "0 0 48 48"
                })}
                {isDesktop ? "저장됨" : ""}
            </button>
            <button
                className={"ProfileCategoryMenu-Button"
                + (category === "Tag" ? ' ProfileCategoryMenu-Selected' : '')}
                onClick={menuOnClick("Tag")}
                >
                {categoryIcon({
                    label: "Tag",
                    className: "ProfileCategoryMenu-Icon",
                    fill: category === "Tag" ? selectedColor :"#8e8e8e",
                    width: isDesktop ? 12 : 24,
                    height: isDesktop ? 12 : 24,
                    viewBox: "0 0 48 48"
                })}
                {isDesktop ? "태그됨" : ""}
            </button>
        </ProfileCategoryMenuBox>
    );
}

export default ProfileCategoryMenu;