import { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Header } from './layout/Header';
import { Footer } from './layout/Footer';
import { RecipeProvider } from './context/RecipeContext';
import { AuthProvider } from './context/AuthContext';

// Pages
import { Home } from './pages/Home';
import { Recipes } from './pages/Recipes';
import { RecipeDetail } from './pages/RecipeDetail';
import { Login } from './pages/Login';
import { Register } from './pages/Register';
import { Profile } from './pages/Profile';
import { Settings } from './pages/Settings';
import { NotFound } from './pages/NotFound';
import { UserProfile } from './pages/UserProfile';




function App() {
  return (
    <Router>
      <AuthProvider>
        <RecipeProvider>
          <div className="d-flex flex-column min-vh-100">
            <Header />

            <main className="flex-grow-1">
              <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/recipes" element={<Recipes />} />
                <Route path="/recipe/:id" element={<RecipeDetail />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/user/:username" element={<UserProfile />} />
                <Route path="/settings" element={<Settings />} />
                <Route path="*" element={<NotFound />} />
              </Routes>
            </main>

            <Footer />
          </div>
        </RecipeProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
