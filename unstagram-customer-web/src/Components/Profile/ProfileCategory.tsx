import React, { MouseEventHandler, useState } from 'react';
import ProfileCategoryBox from 'Styles/Profile/ProfileCategoryBox';
import ProfileCategoryMenuBox from 'Components/Profile/ProfileCategoryMenu';

const ProfileCategory: React.FC = () => {
    const [category, setCategory] = useState<string>('Board');

    const menuOnClick: Function = (ctg: string): MouseEventHandler => (event: React.MouseEvent<HTMLButtonElement, MouseEvent>): void => {
        setCategory(ctg);
    }

    return (
        <ProfileCategoryBox>
            <ProfileCategoryMenuBox
                category={category}
                menuOnClick={menuOnClick}
                />
        </ProfileCategoryBox>
    );
}

export default ProfileCategory;