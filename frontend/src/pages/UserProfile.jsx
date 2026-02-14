import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { useRecipes } from '../context/RecipeContext';
import { useAuth } from '../context/AuthContext';
import { ArrowLeft, UserPlus, UserMinus } from 'lucide-react';
import { RecipeCard } from '../components/RecipeCard';

export function UserProfile() {
    const { username } = useParams();
    const navigate = useNavigate();
    const { recipes } = useRecipes();
    const { user, followUser, unfollowUser, getUserByUsername } = useAuth();
    const [activeTab, setActiveTab] = useState('recipes');
    const [searchTerm, setSearchTerm] = useState('');
    const [categoryFilter, setCategoryFilter] = useState('All');

    // Get the profile user's data
    const profileUser = getUserByUsername(username);

    // Check if current user is following this profile
    const isFollowing = user?.following?.includes(username) || false;

    // Filter recipes by author and public status
    const userRecipes = recipes.filter(r => r.author === username && r.isPublic);

    // Apply search and filter
    const filteredRecipes = userRecipes.filter(recipe => {
        const matchesSearch = recipe.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
            recipe.description.toLowerCase().includes(searchTerm.toLowerCase());
        const matchesCategory = categoryFilter === 'All' || recipe.category === categoryFilter;
        return matchesSearch && matchesCategory;
    });

    const handleFollowToggle = () => {
        if (isFollowing) {
            unfollowUser(username);
        } else {
            followUser(username);
        }
    };

    if (!profileUser) {
        return (
            <div className="container py-5 text-center animate-fade-in">
                <h2 className="display-6 fw-bold">User not found</h2>
                <button onClick={() => navigate('/')} className="btn btn-primary mt-3">
                    Back to Home
                </button>
            </div>
        );
    }

    return (
        <div className="container py-5 animate-fade-in">
            <button
                onClick={() => navigate(-1)}
                className="btn btn-link text-decoration-none text-muted mb-4 p-0 d-flex align-items-center gap-2"
            >
                <ArrowLeft size={20} /> Back
            </button>

            {/* Profile Header */}
            <div className="row mb-5">
                <div className="col-12">
                    <div className="card shadow-sm border-0 p-4">
                        <div className="d-flex flex-column flex-md-row align-items-center gap-4">
                            {/* Avatar */}
                            <div className="flex-shrink-0">
                                <img
                                    src={profileUser.avatar}
                                    alt={profileUser.name}
                                    className="rounded-circle"
                                    style={{ width: '120px', height: '120px', objectFit: 'cover' }}
                                />
                            </div>

                            {/* User Info */}
                            <div className="flex-grow-1 text-center text-md-start">
                                <h2 className="fw-bold mb-1">{profileUser.name}</h2>
                                <p className="text-muted mb-2">@{username}</p>
                                {profileUser.bio && (
                                    <p className="text-muted mb-3">{profileUser.bio}</p>
                                )}
                                <div className="d-flex gap-4 justify-content-center justify-content-md-start">
                                    <div>
                                        <span className="fw-bold">{userRecipes.length}</span>
                                        <span className="text-muted ms-1">Recipes</span>
                                    </div>
                                    <div>
                                        <span className="fw-bold">{profileUser.followers || 0}</span>
                                        <span className="text-muted ms-1">Followers</span>
                                    </div>
                                </div>
                            </div>

                            {/* Follow Button - hide only if viewing own profile */}
                            {(!user || user.username !== username) && (
                                <div className="flex-shrink-0">
                                    <button
                                        onClick={() => {
                                            if (!user) {
                                                navigate('/login');
                                            } else {
                                                handleFollowToggle();
                                            }
                                        }}
                                        className={`btn ${isFollowing ? 'btn-outline-primary' : 'btn-primary'} d-flex align-items-center gap-2`}
                                    >
                                        {isFollowing ? (
                                            <>
                                                <UserMinus size={20} />
                                                Unfollow
                                            </>
                                        ) : (
                                            <>
                                                <UserPlus size={20} />
                                                Follow
                                            </>
                                        )}
                                    </button>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            </div>

            {/* Search and Filter */}
            <div className="row g-3 mb-4">
                <div className="col-md-8">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Search recipes..."
                        value={searchTerm}
                        onChange={e => setSearchTerm(e.target.value)}
                    />
                </div>
                <div className="col-md-4">
                    <select
                        className="form-select"
                        value={categoryFilter}
                        onChange={e => setCategoryFilter(e.target.value)}
                    >
                        <option value="All">All Categories</option>
                        <option value="Breakfast">Breakfast</option>
                        <option value="Lunch">Lunch</option>
                        <option value="Dinner">Dinner</option>
                        <option value="Dessert">Dessert</option>
                        <option value="Snack">Snack</option>
                    </select>
                </div>
            </div>

            {/* Recipe Grid */}
            {filteredRecipes.length > 0 ? (
                <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
                    {filteredRecipes.map(recipe => (
                        <div key={recipe.id} className="col">
                            <RecipeCard recipe={recipe} />
                        </div>
                    ))}
                </div>
            ) : (
                <div className="text-center py-5">
                    <p className="text-muted">No public recipes found.</p>
                </div>
            )}
        </div>
    );
}
