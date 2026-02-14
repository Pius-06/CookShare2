import React, { createContext, useState, useContext } from 'react';

const AuthContext = createContext();

// Mock users database
const MOCK_USERS = {
    'testuser': {
        username: 'testuser',
        name: 'Test User',
        firstname: 'Test',
        lastname: 'User',
        email: 'test@example.com',
        avatar: 'https://ui-avatars.com/api/?name=Test+User&background=f97316&color=fff',
        bio: 'Passionate about cooking and sharing recipes!',
        followers: 42
    },
    'Chef John': {
        username: 'Chef John',
        name: 'Chef John',
        firstname: 'John',
        lastname: '',
        email: 'chefj@example.com',
        avatar: 'https://ui-avatars.com/api/?name=Chef+John&background=2EC4B6&color=fff',
        bio: 'Professional chef with 15 years of experience in Thai cuisine.',
        followers: 1204
    },
    'HealthyEats': {
        username: 'HealthyEats',
        name: 'Emma Wilson',
        firstname: 'Emma',
        lastname: 'Wilson',
        email: 'emma@healthyeats.com',
        avatar: 'https://ui-avatars.com/api/?name=Emma+Wilson&background=FF6B35&color=fff',
        bio: 'Nutritionist and healthy food advocate. Making wellness delicious!',
        followers: 856
    },
    'SmoothieQueen': {
        username: 'SmoothieQueen',
        name: 'Sarah Miller',
        firstname: 'Sarah',
        lastname: 'Miller',
        email: 'sarah@smoothiequeen.com',
        avatar: 'https://ui-avatars.com/api/?name=Sarah+Miller&background=9B59B6&color=fff',
        bio: 'Smoothie enthusiast and wellness blogger. Blending up happiness daily!',
        followers: 623
    },
    'ItalianMaster': {
        username: 'ItalianMaster',
        name: 'Marco Rossi',
        firstname: 'Marco',
        lastname: 'Rossi',
        email: 'marco@italian.com',
        avatar: 'https://ui-avatars.com/api/?name=Marco+Rossi&background=E74C3C&color=fff',
        bio: 'Born in Rome, trained in Milan. Bringing authentic Italian flavors to your kitchen.',
        followers: 2103
    }
};

export function AuthProvider({ children }) {
    const [user, setUser] = useState(null); // null means not logged in

    const login = (email) => {
        setUser({
            firstname: "Test",
            lastname: "User",
            name: "Test User",
            username: "testuser",
            email,
            avatar: "https://ui-avatars.com/api/?name=Test+User&background=f97316&color=fff",
            bio: "Passionate about cooking and sharing recipes!",
            following: []
        });
    };

    const logout = () => {
        setUser(null);
    };

    const register = (userData) => {
        // Mock registration logic
        const { username, firstname, lastname, email } = userData;
        setUser({
            firstname,
            lastname,
            name: `${firstname} ${lastname}`,
            username,
            email,
            avatar: `https://ui-avatars.com/api/?name=${firstname}+${lastname}&background=f97316&color=fff`,
            bio: "Food enthusiast and home cook.",
            following: []
        });
    }

    const updateUser = (updatedData) => {
        setUser(prev => {
            const newState = { ...prev, ...updatedData };
            // Auto-update full name and avatar if names change
            if (updatedData.firstname || updatedData.lastname) {
                newState.name = `${newState.firstname} ${newState.lastname}`;
                newState.avatar = `https://ui-avatars.com/api/?name=${newState.firstname}+${newState.lastname}&background=f97316&color=fff`;
            }
            return newState;
        });
    };

    const followUser = (username) => {
        setUser(prev => ({
            ...prev,
            following: [...(prev.following || []), username]
        }));
    };

    const unfollowUser = (username) => {
        setUser(prev => ({
            ...prev,
            following: (prev.following || []).filter(u => u !== username)
        }));
    };

    const getUserByUsername = (username) => {
        return MOCK_USERS[username] || null;
    };

    return (
        <AuthContext.Provider value={{
            user,
            login,
            logout,
            register,
            updateUser,
            followUser,
            unfollowUser,
            getUserByUsername
        }}>
            {children}
        </AuthContext.Provider>
    );
}

export function useAuth() {
    return useContext(AuthContext);
}
