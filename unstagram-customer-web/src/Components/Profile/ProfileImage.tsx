import React from 'react';
import ProfileImageBox from 'Styles/Profile/ProfileImageBox';

const ProfileImage: React.FC = () => {

    return (
        <ProfileImageBox>
            <button className="ProfileImage-Button">
                <img  className="ProfileImage-Image"/>
            </button>
        </ProfileImageBox>
    );
}

export default ProfileImage;