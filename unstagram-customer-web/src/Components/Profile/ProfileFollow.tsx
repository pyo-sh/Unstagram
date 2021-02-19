import React from 'react';
import ProfileFollowBox from 'Styles/Profile/ProfileFollowBox';

const ProfileFollow: React.FC = () => {
    
    return (
        <ProfileFollowBox>
            <li className="ProfileFollow-List">
                게시물&nbsp;
                <span className="ProfileFollow-Count">
                    521
                </span>
            </li>
            <li className="ProfileFollow-List">
                <a className="ProfileFollow-Link">
                    팔로워&nbsp;
                    <span className="ProfileFollow-Count">
                        86.1
                    </span>
                </a>
            </li>
            <li className="ProfileFollow-List">
                <a className="ProfileFollow-Link">
                    팔로우&nbsp;
                    <span className="ProfileFollow-Count">
                        76
                    </span>
                </a>
            </li>
        </ProfileFollowBox>
    );
}

export default ProfileFollow;